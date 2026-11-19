const $ = (id) => document.getElementById(id);
let currentRole = null;
async function api(path, options = {}) {
  const response = await fetch(path, { credentials: 'same-origin', headers: { 'Content-Type': 'application/json', ...(options.headers || {}) }, ...options });
  const payload = response.status === 204 ? null : await response.json().catch(() => ({}));
  if (!response.ok) throw new Error(payload?.error || payload?.message || `Request failed (${response.status})`);
  return payload;
}
function message(id, text, success = false) { const el = $(id); el.textContent = text; el.className = `message ${success ? 'success' : ''}`; }
function showSignedOut() { $('loginPanel').classList.remove('hidden'); $('dashboard').classList.add('hidden'); $('sessionStatus').textContent = 'Not signed in'; }
async function loadDashboard() {
  const me = await api('/api/auth/me');
  currentRole = me.role;
  $('sessionStatus').textContent = `${me.username} · ${me.role}`;
  $('loginPanel').classList.add('hidden'); $('dashboard').classList.remove('hidden');
  $('welcome').textContent = me.role === 'ADMIN' ? 'Administration dashboard' : 'Welcome back';
  $('adminPanel').classList.toggle('hidden', me.role !== 'ADMIN');
  $('requestForm').classList.toggle('hidden', me.role === 'ADMIN');
  if (me.role === 'ADMIN') {
    const students = await api('/api/students');
    $('studentList').innerHTML = `<table><thead><tr><th>Roll</th><th>Name</th><th>Marks</th><th>Attendance</th><th>Result</th></tr></thead><tbody>${students.map(s => `<tr><td>${escapeHtml(s.rollNumber)}</td><td>${escapeHtml(s.fullName)}</td><td>${s.marks}</td><td>${s.attendancePercent}%</td><td>${escapeHtml(s.result)}</td></tr>`).join('')}</tbody></table>`;
    $('studentName').textContent = `${students.length} students`;
    $('studentRoll').textContent = 'Registered records'; $('attendance').textContent = '—'; $('marks').textContent = '—'; $('result').textContent = '—';
    $('profile').textContent = 'Admin view: use the student records table to inspect current sample data.';
  } else {
    const student = await api('/api/students/me');
    $('studentName').textContent = student.fullName; $('studentRoll').textContent = student.rollNumber;
    $('attendance').textContent = `${student.attendancePercent}%`; $('marks').textContent = student.marks; $('result').textContent = student.result;
    $('profile').innerHTML = `<div><span>Roll number</span><b>${escapeHtml(student.rollNumber)}</b></div><div><span>Email</span><b>${escapeHtml(student.email)}</b></div><div><span>Department</span><b>${escapeHtml(student.department)}</b></div><div><span>Semester</span><b>${student.semester}</b></div>`;
  }
  await loadRequests();
}
function escapeHtml(value) { return String(value).replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c])); }
function setRequestListMessage(text, success = false) {
  const el = $('requestListMessage');
  if (!el) return;
  el.textContent = text;
  el.className = `message ${success ? 'success' : ''}`;
}
function statusLabel(status) {
  return ({ OPEN: 'Open', IN_PROGRESS: 'In Progress', RESOLVED: 'Resolved', REJECTED: 'Rejected' })[status] || status;
}
async function loadRequests() {
  const list = await api('/api/requests');
  $('requestList').innerHTML = list.length ? list.map(r => {
    const isClosed = r.status === 'RESOLVED' || r.status === 'REJECTED';
    const adminControls = currentRole === 'ADMIN'
      ? (isClosed
        ? '<p class="muted request-closed-note">This request is closed and cannot be changed.</p>'
        : `<div class="request-actions"><label for="requestStatus-${r.id}">Update status<select id="requestStatus-${r.id}" class="request-status-select"><option value="OPEN" ${r.status === 'OPEN' ? 'selected' : ''}>Open</option><option value="IN_PROGRESS" ${r.status === 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option><option value="RESOLVED">Resolved</option><option value="REJECTED">Rejected</option></select></label><button class="secondary request-update-button" type="button" data-update-request="${r.id}">Save status</button></div>`)
      : '';
    return `<details class="request-item"><summary class="request-summary"><span class="request-summary-main"><strong>${escapeHtml(r.title)}</strong><small>${escapeHtml(r.rollNumber)} · ${new Date(r.createdAt).toLocaleString()}</small></span><span class="request-summary-side"><span class="status">${escapeHtml(statusLabel(r.status))}</span><span class="request-expand-hint">View details ▾</span></span></summary><div class="request-detail"><p class="request-description">${escapeHtml(r.description)}</p>${adminControls}</div></details>`;
  }).join('') : 'No requests yet.';
}
$('loginForm').addEventListener('submit', async (event) => {
  event.preventDefault(); message('loginMessage', 'Signing in…');
  try { await api('/api/auth/login', { method: 'POST', body: JSON.stringify({ username: $('username').value.trim(), password: $('password').value }) }); await loadDashboard(); }
  catch (error) { message('loginMessage', error.message); }
});
$('logoutBtn').addEventListener('click', async () => { try { await api('/api/auth/logout', { method: 'POST', body: '{}' }); } finally { showSignedOut(); } });
$('requestForm').addEventListener('submit', async (event) => {
  event.preventDefault();
  try { await api('/api/requests', { method: 'POST', body: JSON.stringify({ title: $('requestTitle').value.trim(), description: $('requestDescription').value.trim() }) }); $('requestForm').reset(); message('requestMessage', 'Request submitted.', true); await loadRequests(); }
  catch (error) { message('requestMessage', error.message); }
});
$('refreshBtn').addEventListener('click', () => loadRequests().then(() => setRequestListMessage('Requests refreshed.', true)).catch(error => setRequestListMessage(error.message)));
$('requestList').addEventListener('click', async (event) => {
  const button = event.target.closest('[data-update-request]');
  if (!button) return;
  const requestId = button.dataset.updateRequest;
  const select = $(`requestStatus-${requestId}`);
  if (!select) return;
  button.disabled = true;
  button.textContent = 'Saving…';
  setRequestListMessage('Updating request status…');
  try {
    const updated = await api(`/api/requests/${encodeURIComponent(requestId)}/status`, { method: 'PATCH', body: JSON.stringify({ status: select.value }) });
    await loadRequests();
    setRequestListMessage(`Request #${updated.id} updated to ${statusLabel(updated.status)}.`, true);
  } catch (error) {
    setRequestListMessage(error.message);
    button.disabled = false;
    button.textContent = 'Save status';
  }
});
api('/api/auth/me').then(loadDashboard).catch(showSignedOut);

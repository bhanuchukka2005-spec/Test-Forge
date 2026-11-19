const $ = (id) => document.getElementById(id);
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
async function loadRequests() {
  const list = await api('/api/requests');
  $('requestList').innerHTML = list.length ? list.map(r => `<div class="request-item"><div><strong>${escapeHtml(r.title)}</strong><small>${escapeHtml(r.rollNumber)} · ${new Date(r.createdAt).toLocaleString()}</small></div><span class="status">${escapeHtml(r.status)}</span></div>`).join('') : 'No requests yet.';
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
$('refreshBtn').addEventListener('click', () => loadRequests().catch(error => message('requestMessage', error.message)));
api('/api/auth/me').then(loadDashboard).catch(showSignedOut);

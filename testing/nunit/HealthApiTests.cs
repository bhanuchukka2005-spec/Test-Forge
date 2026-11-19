using System.Net;
using NUnit.Framework;

namespace TestForge.ApiTests;

public class HealthApiTests
{
    private static readonly HttpClient Client = new() { BaseAddress = new Uri(Environment.GetEnvironmentVariable("TESTFORGE_URL") ?? "http://localhost:8080") };

    [Test]
    public async Task HealthEndpointReturnsOk()
    {
        var response = await Client.GetAsync("/api/health");
        Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.OK));
        var body = await response.Content.ReadAsStringAsync();
        Assert.That(body, Does.Contain("UP"));
    }
}

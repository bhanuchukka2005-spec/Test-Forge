package com.testforge

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class HealthSimulation extends Simulation {
  private val baseUrl = sys.env.getOrElse("TESTFORGE_URL", "http://localhost:8080")
  private val httpProtocol = http.baseUrl(baseUrl).acceptHeader("application/json")
  private val healthScenario = scenario("TestForge health endpoint")
    .exec(http("health").get("/api/health").check(status.is(200)))
  setUp(healthScenario.inject(rampUsers(10).during(10.seconds), constantUsersPerSec(2).during(20.seconds)))
    .protocols(httpProtocol)
}

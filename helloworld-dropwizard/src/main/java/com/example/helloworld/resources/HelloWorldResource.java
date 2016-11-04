/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.example.helloworld.resources;

import com.example.helloworld.api.Greeting;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
  private final List<String> templates;
  private final String defaultName;
  private final Random random;

  public HelloWorldResource(List<String> templates, String defaultName) {
    this.templates = templates;
    this.defaultName = defaultName;
    this.random = new Random();
  }

  @GET
  @Timed
  public Greeting sayHello(@QueryParam("name") Optional<String> nameParam) {
    String name = nameParam.orElse(defaultName);
    String template = templates.get(random.nextInt(templates.size()));
    String value = String.format(template, name);
    return new Greeting(name, value);
  }
}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * license agreements; and to You under the Apache License, version 2.0:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * This file is part of the Apache Pekko project, which was derived from Akka.
 */

/*
 * Copyright (C) 2017-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package org.apache.pekko.http.scaladsl.marshallers.sprayjson

import scala.concurrent.ExecutionContext

import org.apache.pekko
import pekko.actor.ActorSystem
import pekko.http.scaladsl.marshalling.Marshal
import pekko.http.scaladsl.model.MessageEntity
import pekko.http.scaladsl.unmarshalling.Unmarshal
import pekko.stream.ActorMaterializer
import pekko.util.ByteString
import org.scalatest.concurrent.ScalaFutures
import spray.json.{ JsArray, JsString, JsValue }
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spray.json.RootJsonFormat

class SprayJsonSupportSpec extends AnyWordSpec with Matchers with ScalaFutures {
  import SprayJsonSupport._
  import SprayJsonSupportSpec._
  import spray.json.DefaultJsonProtocol._

  implicit val exampleFormat: RootJsonFormat[Example] = jsonFormat1(Example.apply)
  implicit val sys: ActorSystem = ActorSystem("SprayJsonSupportSpec")
  implicit val mat: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContext = sys.dispatcher

  val TestString =
    "Contains all UTF-8 characters: 2-byte: £, 3-byte: ﾖ, 4-byte: 😁, 4-byte as a literal surrogate pair: \uD83D\uDE01"

  "SprayJsonSupport" should {
    "allow round trip via Marshal / Unmarshal case class <-> HttpEntity" in {
      val init = Example(TestString)

      val js = Marshal(init).to[MessageEntity].futureValue
      val example = Unmarshal(js).to[Example].futureValue

      example should ===(init)
    }
    "allow round trip via Marshal / Unmarshal JsValue <-> HttpEntity" in {
      val init = JsArray(JsString(TestString))

      val js = Marshal(init).to[MessageEntity].futureValue
      val example = Unmarshal(js).to[JsValue].futureValue

      example should ===(init)
    }
    "allow Unmarshalling from ByteString -> case class" in {
      val init = Example(TestString)
      val js = ByteString(s"""{"username": "$TestString"}""")
      val example = Unmarshal(js).to[Example].futureValue

      example should ===(init)
    }
  }
}

object SprayJsonSupportSpec {
  case class Example(username: String)
}

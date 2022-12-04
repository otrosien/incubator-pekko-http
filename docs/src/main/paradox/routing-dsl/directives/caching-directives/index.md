# CachingDirectives

Use these directives to "wrap" expensive operations with a caching layer that
runs the wrapped operation only once and returns the the cached value for all
future accesses for the same key (as long as the respective entry has not expired).
See @ref[caching](../../../common/caching.md) for an introduction to how the
caching support works.

## Dependency

To use Apache Pekko HTTP Caching, add the module to your project:

@@dependency[sbt,Gradle,Maven] {
  bomGroup2="org.apache.pekko" bomArtifact2="pekko-http-bom_$scala.binary.version$" bomVersionSymbols2="AkkaHttpVersion"
  symbol="AkkaHttpVersion"
  value="$project.version$"
  group="org.apache.pekko"
  artifact="pekko-http-caching_$scala.binary.version$"
  version="AkkaHttpVersion"
}

## Imports

Directives are available by importing:

Scala
:  @@snip [HeaderDirectivesExamplesSpec.scala]($root$/src/test/scala/docs/http/scaladsl/server/directives/CachingDirectivesExamplesSpec.scala) { #caching-directives-import }

Java
:   @@snip [CachingDirectivesExamplesTest.java]($root$/src/test/java/docs/http/javadsl/server/directives/CachingDirectivesExamplesTest.java) { #caching-directives-import }

@@toc { depth=1 }

@@@ index

* [cache](cache.md)
* [alwaysCache](alwaysCache.md)
* [cachingProhibited](cachingProhibited.md)

@@@

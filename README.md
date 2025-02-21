# javaspawnprocess




gramine-argv-serializer "/usr/lib/jvm/msopenjdk-current/bin/java" "-Xms256m" "-Xmx256m" "-XX:CompressedClassSpaceSize=128m" "-XX:MaxMetaspaceSize=256m" "-XX:MaxDirectMemorySize=134217728" "-cp" "/workspaces/JavaSpawnProcess/target/classes" "com.github.nmwael.jna.Main" > jvm_args.txt


gramine-argv-serializer "/usr/lib/jvm/java-21-openjdk-amd64/bin/java" "-Xms64m" "-Xmx64m" "-XX:MaxDirectMemorySize=16m" "-XX:-UseCompressedClassPointers" "-XX:MaxMetaspaceSize=128m" "-XX:ReservedCodeCacheSize=16m" "-cp" "/home/strongnino/jspawnhelper/target/classes" "com.github.nmwael.jna.Main" > jvm_args.txt

gramine-manifest -Darch_libdir=/lib/x86_64-linux-gnu java_spawn.manifest.template java_spawn.manifest

gramine-direct java_spawn

gramine-sgx-sign --manifest java_spawn.manifest	--output java_spawn.manifest.sgx

strace --output=/workspaces/JavaSpawnProcess/java-helloworld.strace.log -f /usr/lib/jvm/msopenjdk-current/bin/java -XX:+ShowCodeDetailsInExceptionMessages -cp /workspaces/JavaSpawnProcess/target/classes com.github.nmwael.jna.Main
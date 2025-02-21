# javaspawnprocess

sudo curl -fsSLo /usr/share/keyrings/gramine-keyring.gpg https://packages.gramineproject.io/gramine-keyring.gpg
echo "deb [arch=amd64 signed-by=/usr/share/keyrings/gramine-keyring.gpg] https://packages.gramineproject.io/ $(lsb_release -sc) main" \
| sudo tee /etc/apt/sources.list.d/gramine.list

sudo curl -fsSLo /usr/share/keyrings/intel-sgx-deb.asc https://download.01.org/intel-sgx/sgx_repo/ubuntu/intel-sgx-deb.key
echo "deb [arch=amd64 signed-by=/usr/share/keyrings/intel-sgx-deb.asc] https://download.01.org/intel-sgx/sgx_repo/ubuntu jammy main" \
| sudo tee /etc/apt/sources.list.d/intel-sgx.list

sudo apt-get update
sudo apt-get install gramine



gramine-argv-serializer "/usr/lib/jvm/msopenjdk-current/bin/java" "-Xms256m" "-Xmx256m" "-XX:CompressedClassSpaceSize=128m" "-XX:MaxMetaspaceSize=256m" "-cp /workspaces/JavaSpawnProcess/target/classes" "com.github.nmwael.jna.Main" > jvm_args.txt

gramine-manifest -Darch_libdir=/lib/x86_64-linux-gnu java_spawn.manifest.template java_spawn.manifest

gramine-direct java_spawn

gramine-sgx-sign \
		--manifest $< \
		--output $<.sgx

strace --output=/workspaces/JavaSpawnProcess/java-helloworld.strace.log -f /usr/lib/jvm/msopenjdk-current/bin/java -XX:+ShowCodeDetailsInExceptionMessages -cp /workspaces/JavaSpawnProcess/target/classes com.github.nmwael.jna.Main
*Prerequisites*
* Tomcat 6+

*Configuration*

1. Add your google credentials to application.properties
2. Add your google user to src/main/sample/application/dao/impl/HardCodedUserDetailsDAO i.e. change users.put("test.manager@gmail.com", managerRoles);
3. build.gradle configure cargo to point at your Tomcat:

```java
cargo {
	containerId = 'tomcat7x'
	port = 8080
 
	local {
		homeDir = file('/ApplicationServers/apache-tomcat-7.0.41')
		output = file('/ApplicationServers/apache-tomcat-7.0.41/output.log')
	}
}
```
*Running*

1. gradle clean build
2. gradle cargoStartLocal
3. Browser http://localhost:8080/sample-security-google-auth/

(Should see welcome)

4. Click link 'Click To Enter Sample Application'
5a. If not already logged into Google, you will be redirected prompted for your credentials then redirected back and displayed:

Hello User

5b. If you were already logged in, you should be just be redirected and presented with the same message as above.

*TODOs*

1. Replace System.outs with Log4J
2. Secure (HTTPS) redirect URL to protect against interception of token.
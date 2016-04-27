# ezframework


### Dependencies
```groovy
dependencies {
	compile "org.ezdevgroup:ezframework-web:$ezframeworkVersion"
	compile "org.ezdevgroup:ezframework-support:$ezframeworkVersion"
	compile "org.ezdevgroup:ezframework-tag:$ezframeworkVersion"
	compile "org.ezdevgroup:ezframework-security:$ezframeworkVersion"
	compile "org.ezdevgroup:ezframework-ehcache:$ezframeworkVersion"
	compile "org.ezdevgroup:ezframework-ispn:$ezframeworkVersion"
	compile "org.ezdevgroup:ezframework-rabbitmq:$ezframeworkVersion"
}
```

### Environment Or OpenSource Library
```
	javaVersion 				= '1.7'
	javaEncoding 				= 'UTF-8'
	springVersion 				= '4.2.5.RELEASE'
	springSecurityVersion 		= '3.2.9.RELEASE'
	infinispanVersion			= '7.2.5.Final'
	infinispanSpringVersion		= '7.2.5.Final'
	fasterxmlJacksonVersion		= '2.4.4'
	ezmorphVersion				= '1.0.6'
	commonsLangVersion			= '2.6'
	commonsBeanutilsVersion		= '1.9.1'
	commonsCollectionsVersion	= '3.2.1'
	commonsIoVersion			= '2.4'
	commonsFileuploadVersion	= '1.3.1'
	mybatisVersion				= '3.4.0'
	mybatisSpringVersion		= '1.3.0'
	springRabbitVersion 		= '1.5.5.RELEASE'
}
```

### Quick Start
  * [템플릿으로 시작하기](https://github.com/ddakker/ezframework/wiki/%ED%85%9C%ED%94%8C%EB%A6%BF%EC%9C%BC%EB%A1%9C-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0)
  * [구성](https://github.com/ddakker/ezframework/wiki/%EA%B5%AC%EC%84%B1)
  * [전반적 간략 소개](https://github.com/ddakker/ezframework/wiki/%EC%A0%84%EB%B0%98%EC%A0%81-%EA%B0%84%EB%9E%B5-%EC%86%8C%EA%B0%9C)

### Modules
  * [ezframework-web](https://github.com/ddakker/ezframework/wiki/ezframework-web)
  * [ezframework-support](https://github.com/ddakker/ezframework/wiki/ezframework-support)
  * [ezframework-tag](https://github.com/ddakker/ezframework/wiki/ezframework-tag)
  * [ezframework-security](https://github.com/ddakker/ezframework/wiki/ezframework-security)
  * [ezframework-ehcache](https://github.com/ddakker/ezframework/wiki/ezframework-ehcache)
  * [ezframework-ispn](https://github.com/ddakker/ezframework/wiki/ezframework-ispn)
  * [ezframework-rabbitmq](https://github.com/ddakker/ezframework/wiki/ezframework-rabbitmq)

### Example
  * example-web
    - Spring MVC 기반
  * example-web-security
    - Spring MVC + Spring Security 기본 방식
  * example-web-security-custom
    - Spring MVC + Spring Security id/pwd 이외 추가 파라밑미터 활용 방식
# Use of service gateway
- It is a gate keeper who knows all the address inside the system.
- In micro-service world we have lot of services deployed with different host and port, but we dont want to remember all of these.
- In this case we have service gateway wich has routing logic which routes the request as per url.
- Ex: /hotel -> roues to hotel-host:port , /rating -> routes to rating-host:port
- it also load balances the services.
- We are using spring-cloud-gateway but there are other open-source gateways like envoy.

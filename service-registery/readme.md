# Use of service registry
- In the microservice world, tracking the health status of various services deployed in different locations can be challenging
- To overcome this challenge, we can utilize a service registry as a centralized platform to monitor the health of all services
- The services continuously send "I'm alive" pings to the service registry. If a service fails to receive a ping, it is marked as down.

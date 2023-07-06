# Use of config-server service

- Typically spring applications has a config file called application.yml or application.property which is part of jar (you can override it as well).
- Spring application sometimes need to handle configuration in some version control place to track the changes.
- As it is pushed in some other place config and code become decoumpled means they can be build parallely.
- Also it is easy to rollback in case of wrong config went in without rebuilding the eatire project/jar.

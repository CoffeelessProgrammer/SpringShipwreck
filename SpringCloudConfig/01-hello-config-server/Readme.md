# Config Server POC

Setting up a Spring Config Server with local machine property file storage.

## Problems Encountered
- **Issue:** `user.home` env variable on Windows uses backslashes instead of forward slashes, e.g. `C:\Users\ryo`. This results in paths with both forward and backslashes... `file:///C:\Users\ryo/Documents/Playground/JavaSandbox/spring-config-local`
    - **Solution:** Unknown, however Spring was eventually able to locate the Config directory after a few service restarts?!
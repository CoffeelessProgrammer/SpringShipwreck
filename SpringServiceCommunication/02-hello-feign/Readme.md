# Service Discovery POC

## Resources
- [Feign Client Basics | Codeboje](https://codeboje.de/getting-started-feignclient/)
- [Calling another service with Feign | AppsDeveloperBlog](https://www.appsdeveloperblog.com/feign-client-to-call-another-microservice/)
- [Feign ErrorDecoder | Baeldung](https://www.baeldung.com/feign-retrieve-original-message)


## Problems Encountered
- **Issue:** If conditonal not triggering - `methodKey.endsWith("addToTrainerParty")`.
    - **Solution:** The method key goes beyond method name to include parameters, e.g. `TrainerPartyServiceClient#addToTrainerParty(PartyPokemonCM,long)`.
    - Modified conditonal - `methodKey.contains("#addToTrainerParty")`
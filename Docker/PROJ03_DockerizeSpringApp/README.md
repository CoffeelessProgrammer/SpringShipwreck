# Dockerizing Spring App

## Data Models

```mermaid
classDiagram
    class Job {
	    -String id
	    -String description
	    -String company
	    -Set< String> skills
	    -Integer salary
	    -Boolean salary
    }
    class JobRepository {
	    +findBySkillsIn(Set skills) Flux< Job>
    }
```

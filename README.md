# Workshop 16

## Dependencies to add
- Json Dependency
```
<!-- https://mvnrepository.com/artifact/org.glassfish/jakarta.json -->
<dependency>
	<groupId>org.glassfish</groupId>
	<artifactId>jakarta.json</artifactId>
	<version>2.0.1</version>
</dependency>
```
- Jedis Dependency
```
<dependency>
	<groupId>redis.clients</groupId>
	<artifactId>jedis</artifactId>
	<version>4.3.1</version>
</dependency>
```

## Local Testing
- Setting up
```
set REDISHOST localhost
```
```
set REDISPORT 6379
```
## Mastermind
```
{
    "name": "Mastermind",
    "pieces": {
        "decoding_board": {
            "total_count": 1
        },
        "pegs": {
            "total_count": 102,
            "types": [
                {
                    "type": "code",
                    "count": 72
                },
                {
                    "type": "key",
                    "count": 30
                }
            ]
        },
        "rulebook": {
            "total_count": 1,
            "file": "rulebook-ultimate-mastermind.pdf"
        }
    }
}
```
1. we can see that 6 classes is needed for this JSON
2. Therefore, 6 models are needed

## Key Things to note in this workshop
- All variables need to follow the Json file naming
### BoardGameController
1. The controller used
```
@RestController
```
For Json it is using rest controller not purely controller
2. The request mapping
```
@RequestMapping(path="/api/boardgame", consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
```
For Json, in the request mapping there is consume and produce
3. This part will fufil GET /api/boardgame/<boardgame id>
```
@GetMapping(path="{msId}")
```
## Models
* all need to have getter and setter for all variables
### DecodingBoard
1. DecodingBoard only has total_count **var** therefore only 1 variable
2.Need to convert it to a Json Object using the following code
```
public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder()
                .add("totalCount", this.getTotal_count());
    }
```
### Mastermind
1. Mastermind has 6 variables
2. insertCount and updateCount is needed as stated in the workshop
3.isUpSert is based on task 3
### Pegs
1. Pegs has total_count **var** and types **obj**
2. total_count needs to be converted to **JsOn obj**
3. types needs to be converted to **Json obj**
4. Code
```
public JsonObjectBuilder toJSON(){
        JsonArrayBuilder arrbld = Json.createArrayBuilder();
        List<JsonObjectBuilder> listOfTypes = this.getTypes()
                            .stream()
                            .map(t -> t.toJSON())
                            .toList();
        for (JsonObjectBuilder x : listOfTypes)
            arrbld.add(x);

        return Json.createObjectBuilder()
                .add("total_count", this.getTotal_count())
                .add("types", arrbld);
```
### Pieces
1. Pieces have 4 **obj** decoding_board, pegs and rulebook
2. All need to be converted to **Json Obj**
3. Code
```
public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder()
                .add("decoding_board", this.getDecoding_board().toJSON())
                .add("pegs", this.getPegs().toJSON())
                .add("rulebook", this.getRulebook().toJSON());      
    }
```
### Rulebook
1. Rulebook has total_count **var** and file **obj**
2. All need to be converted to **Json Obj**
3. Code
```
public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder()
                .add("total_count", this.getTotal_count())
                .add("file", this.getFile());
    } 
```
### Type
1. Types have 2 **var** type and count
2. Both need to be converted to **Json Obj**
3. Code
```
public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder()
                .add("type", this.getType())
                .add("count", this.getCount());
                
    } 
```
### BoardGameService
1. This is a service hence @Service is needed
2. KIV on what is a service



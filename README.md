# RestClientAPI - Library to Client REST

**Example**   
```java
EntityRequest entity = new EntityRequest(ProtocolType.HTTP, "93.188.161.66", 8080);
RestClientAPI rest = new RestClientAPI(entity);
EntityResponse restEntityResponse;

try {
	System.out.println("***** GET ***********************************************************");
	entity.setPathTarget("/springrest");
	restEntityResponse = rest.get();
	System.out.println("Response Status = " + restEntityResponse.getHttpStatus().name());
	System.out.println("Response Body = " + restEntityResponse.getHttpBody());

	System.out.println("");
	System.out.println("***** POST ***********************************************************");
	entity.setPathTarget("/springrest/aaa");
	restEntityResponse = rest.post();
	System.out.println("Response Status = " + restEntityResponse.getHttpStatus().name());
	System.out.println("Response Body = " + restEntityResponse.getHttpBody());

	System.out.println("");
	System.out.println("***** PUT ************************************************************");
	entity.setPathTarget("/springrest/aaa/bbbupdated");
	restEntityResponse = rest.put();
	System.out.println("Response Status = " + restEntityResponse.getHttpStatus().name());
	System.out.println("Response Body = " + restEntityResponse.getHttpBody());

	System.out.println("");
	System.out.println("***** DELETE **********************************************************");
	entity.setPathTarget("/springrest/bbbupdated");
	restEntityResponse = rest.delete();
	System.out.println("Response Status = " + restEntityResponse.getHttpStatus().name());
	System.out.println("Response Body = " + restEntityResponse.getHttpBody());

} catch (IOException e) {
	System.out.println("[ERROR] - " + e.getMessage());
}
```   

License
-------
[![MIT][0]][1]   
Licensed under an [MIT-style permissive license][3].   

Open Source
-----------
[![Open Source][2]][3]  

[0]: https://raw.githubusercontent.com/fabianogoes/sigaula/master/doc/mit-license.png
[1]: https://raw.githubusercontent.com/fabianogoes/sigaula/master/LICENSE
[2]: https://raw.githubusercontent.com/fabianogoes/sigaula/master/doc/opensource-iniciative.png
[3]: https://en.wikipedia.org/wiki/Open_Source_Initiative

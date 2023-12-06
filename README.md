Quarkus guide: https://quarkus.io/guides/microprofile-graphql

## GraphQL
```graphql
mutation CreateHero {
    createHero (hero:{darkSide: false, episodeIds: [1,2,3], height: 1.6, lightSaber: BLUE, mass: 42, name: "Padmé", surname: "Amidala"})
{   
    darkSide
    episodeIds
    height
    lightSaber
    mass
    name
    surname
}
}
```
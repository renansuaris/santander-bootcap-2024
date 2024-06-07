# Santander Dev Week 2024
Java RESTful API created for Santander Dev Week.

## Diagrama de Classes

``` mermaid
classDiagram
    class Player {
        - name: String
        - age: int
        - shirtNumber: int
        - height: String
        - country: String
        - position: String
        - club: Club
    }

    class Club {
        - clubName: String
        - clubStadium: String
        - players: List<Player>
        - league: League
    }

    class League {
        - leagueName: String
        - leagueCountry: String
        - teams: List<Club>
    }

    Player --> Club
    Club --> League

```

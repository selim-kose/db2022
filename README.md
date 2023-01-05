# db2022



### Beskrivning


Slutprojekt i kursen DB2022. I detta projekt kommer ska eleven redovisa inom SQL,normalisering samt Java(jdbc) mot en databas.



---


#### Dokumentation

Nedan är ett ER-diagram på UNF-databasen när den har normaliserats.
Diagramet är byggt med [Mermaid](https://mermaid.js.org/syntax/entityRelationshipDiagram.html)

```mermaid

erDiagram
    Student ||--o{ Phone : has
    Student }|--o| Grade : has
    Student ||--o| PhoneList : has
    Student ||--o{ StudentSchool : attends
    School ||--o{ StudentSchool : enrolls
    Student ||--o{ StudentHobby : has
    Hobbies ||--o{ StudentHobby : persues
    
    
    Student {
        int StudentId
        string Name
        int GradeId
    }
    
    Phone {
        int PhoneId
        int StudentId
        varchar Type 
        varchar Number
    }
    PhoneList {
        int StudentId
        text Numbers
    }
    
    School {
        int SchoolId
        varchar School
        varchar City
    }
    
    StudentSchool {
        int StudentId
        int SchoolId
    }
    
    Hobbies {
        int Id
        string Hobby
    }
    StudentHobby {
        decimal StudentId
        varchar Hobby
    }
    
    Grade {
        int Id
        string Grade
    }
    
```





---

##### Förklaring

![a](https://github.com/miwashi-edu/db2022/blob/main/cardinality-1.png)




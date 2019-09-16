package com.compasso.backend.app.repository.query;


import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

@NamedEntityGraph(name = "City.fetchAll",
        attributeNodes = @NamedAttributeNode("federativeUnit")
)
public class CityFetchAllEntityGraph {
}

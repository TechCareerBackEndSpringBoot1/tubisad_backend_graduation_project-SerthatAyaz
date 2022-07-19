package com.hamitmizrak.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "todo")
public class TodoEntity extends BBaseEntity {
    private String todoName;
    private String todoSurname;
}

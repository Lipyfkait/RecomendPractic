package ru.ithub.examination.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest implements Serializable {
    private String username;
    private String firstName;
    private String lastName;
    private String middleName;
    private String password;
}

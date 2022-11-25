package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

@AllArgsConstructor
@Data
public class UserDto {
    private String id;
    private String name;
    private String age;
    private String pet;

    public static User fromDto(UserDto userDto) {
        if (userDto.id == null) {
            userDto.id = "0";
        }
        return new User(Long.parseLong(userDto.id),
                userDto.name,
                Integer.parseInt(userDto.age),
                new Pet(0L, userDto.pet));
    }

    public static UserDto toDto(User user) {
        return new UserDto(String.valueOf(user.getId()),
                user.getName(),
                String.valueOf(user.getAge()),
                user.getPet().getBreed());
    }
}

package com.example.module11.service;

import com.example.module11.entity.User;
import com.example.module11.exception.ItemNotFoundException;
import com.example.module11.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    List<User> users;
    @Mock
    private UserRepository repository;
    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(repository);
        users = new ArrayList<>(Arrays.asList(
                new User(1L, "User1", 11),
                new User(2L, "User2", 22)
        ));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("check finding all users")
    void findAll() {
        when(repository.findAll()).thenReturn(users);

        List<User> userList = service.findAll();
        assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("check finding user by Id")
    void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(users.get(0)));

        User user = service.findById(1L);
        assertThat(user).isNotNull().isEqualTo(users.get(0));
    }

    @Test
    @DisplayName("check create user")
    void save() {
        when(repository.save(users.get(0))).thenReturn(users.get(0));
        User user = service.save(users.get(0));
        assertThat(user.getName()).isEqualTo(users.get(0).getName());
    }

    @Test
    @DisplayName("check update user")
    void update() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(users.get(0)));
        when(repository.save(users.get(0))).thenReturn(users.get(0));

        User user = service.update(users.get(0));
        assertThat(user.getName()).isEqualTo(users.get(0).getName());
    }

    @Test
    @DisplayName("check delete user by Id")
    void deleteById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(users.get(0)));
        when(repository.findById(anyLong())).thenReturn(Optional.of(users.get(0)));
        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(1L);
        verify(repository, times(1)).deleteById(anyLong());

    }

    @Test
    @DisplayName("check NotFoundException")
    void notFoundException() {
        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> service.deleteById(5L));
        assertEquals("User not found, id = 5", exception.getMessage());
    }
}
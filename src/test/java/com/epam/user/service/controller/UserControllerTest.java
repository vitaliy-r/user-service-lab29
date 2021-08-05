package com.epam.user.service.controller;

import static com.epam.user.service.test.util.TestDataUtil.TEST_EMAIL;
import static com.epam.user.service.test.util.TestDataUtil.createUserDto;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.user.service.controller.assembler.UserAssembler;
import com.epam.user.service.controller.model.UserModel;
import com.epam.user.service.dto.UserDto;
import com.epam.user.service.service.UserService;
import com.epam.user.service.test.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(TestConfig.class)
class UserControllerTest {

  @MockBean
  private UserService userService;

  @MockBean
  private UserAssembler userAssembler;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getUserTest() throws Exception {
    UserDto userDto = createUserDto();
    UserModel userModel = new UserModel(userDto);

    when(userService.getUser(TEST_EMAIL)).thenReturn(userDto);
    when(userAssembler.toModel(userDto)).thenReturn(userModel);

    mockMvc.perform(get("/api/v1/users/" + TEST_EMAIL))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.email").value(TEST_EMAIL));
  }

}
package com.jojoldu.book.springBoot.domain.posts;

import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class PostsValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void Posts등록_title빈값이면_실패한다() {
        String emptyTitle = "";
        String content = "content";
        String author = "me";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(emptyTitle)
                .content(content)
                .author(author)
                .build();

        Set<ConstraintViolation<PostsSaveRequestDto>> constraintViolations = validator.validate(requestDto);
        assertThat(constraintViolations.size()).isEqualTo(1);

    }

}

package ddd.splearn.domain.shared;

import ddd.splearn.core.util.StringValidator;

import java.util.regex.Pattern;

public record Email (String address) {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    public Email {
        StringValidator.requireNonBlank(address, "이메일");
        if (!EMAIL_PATTERN.matcher(address).matches()) {
            throw new IllegalArgumentException("이메일 형식이 바르지 않습니다: " + address);
        }
    }
}

package com.prolancer.essaymarker.event;

import com.prolancer.essaymarker.db.model.UserInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private UserInfo userInfo;

    public OnRegistrationCompleteEvent(
            UserInfo userInfo) {
        super(userInfo);
        this.userInfo = userInfo;
    }
}

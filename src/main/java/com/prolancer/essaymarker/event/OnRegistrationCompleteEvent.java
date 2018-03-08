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
    private String appUrl;

    public OnRegistrationCompleteEvent(
            UserInfo userInfo, String appUrl) {
        super(userInfo);
        this.userInfo = userInfo;
        this.appUrl = appUrl;
    }
}

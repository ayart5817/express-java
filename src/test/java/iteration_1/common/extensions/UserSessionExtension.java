package iteration_1.common.extensions;

import api.models.CreateUserRequest;
import api.requests.steps.AdminSteps;
import iteration_1.common.annotations.UserSession;
import iteration_1.storage.SessionStorage;
import iteration_1.ui.CreateUserTest;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import ui.pages.BasePage;

import java.util.LinkedList;
import java.util.List;

public class UserSessionExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        //Шаг 1: Что у теста есть анатация UserSession
        UserSession annotation = extensionContext.getRequiredTestMethod().getAnnotation(UserSession.class);
        if (annotation!= null ) {
            int userCount = annotation.value();

            SessionStorage.clear();

            List<CreateUserRequest> users = new LinkedList<>();
            for (int i= 0; i<userCount; i++ ) {
                CreateUserRequest user =  AdminSteps.createUser().getRequest();
                users.add(user);
            }
            SessionStorage.addUsers(users);

            int authAsUser = annotation.auth();
            BasePage.authAsUser(SessionStorage.getUser(authAsUser));

        }
    }
}

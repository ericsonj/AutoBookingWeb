package test.dao;

import test.elements.User;
import test.hibernate.EhElementLogicFacade;


/**
 *
 * @author ejoseph
 */
public class UserDao extends EhElementLogicFacade<User, String>{

    @Override
    public Class<User> getElementClass() {
        return User.class;
    }
}

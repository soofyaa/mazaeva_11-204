package ru.itis.services;

import ru.itis.dao.PetDAO;
import ru.itis.dao.UserDAO;
import ru.itis.dao.UserPetDAO;
import ru.itis.model.Pet;
import ru.itis.model.User;
import ru.itis.utils.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class MainPageService {
    public static void setupUserAttributes(HttpServletRequest req, HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = UserDAO.findUserByUsername(username);
        session.setAttribute("user", user);
        req.setAttribute("user", user);

        if (user != null) {
            int avatarId = user.getAvatarId();
            String avatarData = Helper.getPhotoData(avatarId);
            req.setAttribute("avatarData", avatarData);
        }
    }

    public static List<Pet> getUserPets(User user) {
        List<Integer> userPetIds = UserPetDAO.findPetIdsByUserId(user.getId());
        List<Pet> userPets = new ArrayList<>();

        for (Integer petId : userPetIds) {
            Pet pet = PetDAO.findPetById(petId);
            if (pet != null) {
                userPets.add(pet);
            }
        }

        return userPets;
    }
}

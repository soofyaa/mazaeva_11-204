package ru.itis.services;

import ru.itis.dao.PetDAO;
import ru.itis.dao.UserDAO;
import ru.itis.dao.UserPetDAO;
import ru.itis.model.Pet;
import ru.itis.model.User;
import ru.itis.utils.Helper;
import ru.itis.utils.SessionManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PetService {
    public static int addPet(String petName, String petDescription, HttpServletRequest req) {
        int petId = PetDAO.addPet(petName, petDescription);
        int userId = SessionManager.getUserFromSession(req).getId();

        if (petId > 0 && userId > 0) {
            UserPetDAO.linkPetToUser(petId, userId);
        }
        return petId;
    }

    public static Pet getPetDetails(HttpServletRequest req) {
        List<String> petPhotos = new ArrayList<>();

        int petId = Integer.parseInt(req.getPathInfo().substring(1));
        Pet pet = PetDAO.findPetById(petId);
        List<Integer> ownerIds = UserPetDAO.findUserIdsByPetId(petId);
        List<String> ownerNames = new ArrayList<>();

        if (pet != null) {
            int avatarId = pet.getAvatarId();
            String avatarData = Helper.getPhotoData(avatarId);
            pet.setAvatarData(avatarData);

            for (Integer photoId : pet.getPhotoIds()) {
                String photoData = Helper.getPhotoData(photoId);
                petPhotos.add(photoData);
            }
            pet.setPhotosData(petPhotos);

            for (Integer ownerId : ownerIds) {
                String ownerName = UserDAO.findUsernameById(ownerId);
                ownerNames.add(ownerName);
            }
            pet.setOwners(ownerNames);
        }
        return pet;
    }

    public static Pet getPetById(int petId) {
        return PetDAO.findPetById(petId);
    }

    public static void updatePetInfo(int petId, String name, String description) {
        PetDAO.updatePetInfo(petId, name, description);
    }

    public static void linkPetToUser(String username, int petId) {
        User user = UserDAO.findUserByUsername(username);
        if (user != null) {
            int userId = user.getId();
            boolean isNotOwner = !(UserPetDAO.isPetOwner(userId, petId));
            if (isNotOwner) {
                UserPetDAO.linkPetToUser(petId, userId);
            }
        }
    }


}

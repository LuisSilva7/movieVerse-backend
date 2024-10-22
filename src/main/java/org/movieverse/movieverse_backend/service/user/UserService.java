package org.movieverse.movieverse_backend.service.user;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.model.Cart;
import org.movieverse.movieverse_backend.model.User;
import org.movieverse.movieverse_backend.repository.CartRepository;
import org.movieverse.movieverse_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    @Transactional
    public void createUserAndCartIfNotExists(String name, String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            Cart cart = new Cart();
            cartRepository.save(cart);

            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setCart(cart);
            userRepository.save(user);
        }
    }
}

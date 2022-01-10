package com.nurcan.stocksservice.bootstrap;

import com.nurcan.stocksservice.domain.model.Role;
import com.nurcan.stocksservice.domain.model.RoleType;
import com.nurcan.stocksservice.domain.model.Stock;
import com.nurcan.stocksservice.domain.model.User;
import com.nurcan.stocksservice.repository.RoleRepository;
import com.nurcan.stocksservice.repository.StockRepository;
import com.nurcan.stocksservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final StockRepository stockRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataLoader(StockRepository stockRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.stockRepository = stockRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        /* Role Objects*/
        Role adminRole = new Role();
        adminRole.setName(RoleType.ADMIN);
        adminRole.setCreatedOn(LocalDateTime.now());
        roleRepository.save(adminRole);

        Role userRole = new Role();
        adminRole.setName(RoleType.USER);
        adminRole.setCreatedOn(LocalDateTime.now());
        roleRepository.save(userRole);

        System.out.println("Number of Roles: " + roleRepository.count());

        /* User Objects*/
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("admin");
        Set<Role> adminRoles = new HashSet<Role>();
        adminRoles.add(userRole);
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userRepository.save(adminUser);

        User user = new User();
        user.setUsername("nurcan");
        user.setPassword("123456");
        Set<Role> userRoles = new HashSet<Role>();
        userRoles.add(userRole);
        user.setRoles(userRoles);
        userRepository.save(user);

        System.out.println("Number of Users: " + userRepository.count());

        /* Stock Objects*/
        Stock stock1 = new Stock();
        stock1.setName("dodo1");
        stock1.setCurrentPrice(10);
        stock1.setLastUpdate(LocalDateTime.now());
        stock1.setCreatedBy("SYSTEM");
        stockRepository.save(stock1);

        Stock stock2 = new Stock();
        stock2.setName("dodo2");
        stock2.setCurrentPrice(11);
        stock2.setLastUpdate(LocalDateTime.now());
        stock2.setCreatedBy("SYSTEM");
        stockRepository.save(stock2);


        System.out.println("Number of Stocks: " + stockRepository.count());
    }
}
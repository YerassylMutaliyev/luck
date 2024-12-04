//package com.example.luck.controller;
//
//import org.springframework.web.bind.annotation.*;
//import com.example.luck.dto.PageRequestDto;
//import com.example.luck.model.User;
//import com.example.luck.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//@RestController
//@RequestMapping("/student")
//public class PaginationController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    //pagination
//    @PostMapping
//    public Page<User> getAllUserUsingPagination(@RequestBody PageRequestDto dto){
//
//        Pageable pageable = new PageRequestDto().getPageable(dto);
//        Page<User> userPage = userRepository.findAll(pageable);
//        return userPage;
//    }
//
//}


//second version
package com.example.luck.controller;

import com.example.luck.model.User;
import com.example.luck.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class PaginationController {

    @Autowired
    private UserRepository userRepository;

    // Пагинация с сортировкой и фильтрацией
    @GetMapping
    public Page<User> getFilteredUsers(
            @RequestParam(required = false) String name,       // Фильтр по имени
            @RequestParam(required = false) String email,      // Фильтр по email
            @RequestParam(required = false) String role,       // Фильтр по роли
            @RequestParam(defaultValue = "0") int page,          // Номер страницы
            @RequestParam(defaultValue = "10") int size,         // Размер страницы
            @RequestParam(defaultValue = "id") String sortBy,    // Поле для сортировки
            @RequestParam(defaultValue = "asc") String direction // Направление сортировки
    ) {
        // Настройка сортировки
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        // Создание объекта Pageable
        Pageable pageable = PageRequest.of(page, size, sort);

        // Запрос к репозиторию
        return userRepository.findByFilters(name, email, role, pageable);
    }
}

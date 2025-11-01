package com.example.demo.services;


import com.example.demo.dto.AccountDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountMapper accountMapper;
    @InjectMocks
    private AccountServiceImpl accountServiceimpl;


    @Test
    @DisplayName("createAccount_success")
    void createAccount_success() {
        User user = new User(1L, "Ahmed", "ahmed@example.com", "123ww", User.Role.CUSTOMER, null);

        Account account = new Account(1L, "ac12", 2000.0, Account.accountType.CURRENT, LocalDate.now(), user, null, null);

        AccountDto dto = new AccountDto();
        dto.setAccount_id(1L);
        dto.setAccount_number("ac12");
        dto.setAccount_balance(2000.0);
        dto.setType(Account.accountType.CURRENT);
        dto.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(accountMapper.toEntity(dto)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountMapper.toDto(account)).thenReturn(dto);


        AccountDto result = accountServiceimpl.CreatAccount(dto);
        assertNotNull(result);
        assertEquals("ac12", result.getAccount_number());
        assertEquals(2000.0, result.getAccount_balance());
        assertEquals(1L, result.getUserId());


    }


    @Test
    @DisplayName("createAccount_nosuccess")
    void createAccount_nosuccess() {
        User user = new User(1L, "Ahmed", "ahmed@example.com", "123ww", User.Role.CUSTOMER, null);

        Account account = new Account(1L, "ac12", 2000.0, Account.accountType.CURRENT, LocalDate.now(), user, null, null);

        AccountDto dto = new AccountDto();
        dto.setAccount_id(1L);
        dto.setAccount_number("ac12");
        dto.setAccount_balance(2000.0);
        dto.setType(Account.accountType.CURRENT);
        dto.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(accountMapper.toEntity(dto)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountMapper.toDto(account)).thenReturn(dto);


        AccountDto result = accountServiceimpl.CreatAccount(dto);
        assertNotNull(result);
        assertEquals("ac412", result.getAccount_number());
        assertEquals(2000.0, result.getAccount_balance());
        assertEquals(1L, result.getUserId());


    }


    @Test
    void CreateAccount_NotFoundUserException() {

        AccountDto dto = new AccountDto();
        dto.setUserId(10L);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());// انا هنا ال بتحكم بقول لموكيتو لما ارجعللك كذا يبقي كذا هتقولي مهو فيه يوزر الكلام ده لو بيانات حقيقه طالما قولتله هيرجع فاضي فهينتظر اكسبشن علشان يعدي ويطلع صح

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> accountServiceimpl.CreatAccount(dto));

        assertEquals("User not found", exception.getMessage());


    }


    @Test
    void getAccountsByUser() {
        User user = new User(1L, "Ahmed", "ahmed@example.com", "123ww", User.Role.CUSTOMER, null);
        Account account1 = new Account(1L, "ac12", 2000.0, Account.accountType.CURRENT, LocalDate.now(), user, null, null);
        Account account2 = new Account(2L, "ac122", 2000.0, Account.accountType.SAVINGS, LocalDate.now(), user, null, null);

        AccountDto dto1 = new AccountDto();
        dto1.setAccount_id(1L);
        dto1.setAccount_number("ac12");
        dto1.setAccount_balance(2000.0);
        dto1.setType(Account.accountType.SAVINGS);
        dto1.setUserId(1L);

        AccountDto dto2 = new AccountDto();
        dto2.setAccount_id(1L);
        dto2.setAccount_number("ac12");
        dto2.setAccount_balance(2000.0);
        dto2.setType(Account.accountType.CURRENT);
        dto2.setUserId(1L);


        when(accountRepository.findByUserId(1L)).thenReturn(List.of(account1,account2));
        when(accountMapper.toDto(account1)).thenReturn(dto1);
        when(accountMapper.toDto(account2)).thenReturn(dto2);


        List<AccountDto> result = accountServiceimpl.getAccountsByUser(user.getId());

        assertNotNull(result);
        assertEquals(2, result.size());


    }


}

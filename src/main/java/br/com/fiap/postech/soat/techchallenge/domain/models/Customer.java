package br.com.fiap.postech.soat.techchallenge.domain.models;

import br.com.fiap.postech.soat.techchallenge.domain.exceptions.*;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class Customer {
    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String phone;

    public Customer() {

    }

    public Customer(UUID id, String name, String cpf, String email, String phone) {
        this.setId(id);
        this.setName(name);
        this.setCpf(cpf);
        this.setEmail(email);
        this.setPhone(phone);
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new InvalidNameException("Customer name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getCpf() {
        return cpf;
    }

    private void setCpf(String cpf) {
//        if (!isValidCpf(cpf)) {
//            throw new InvalidCpfException("Invalid CPF.");
//        }
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!phone.matches("^\\d+$")) throw new InvalidPhoneException("Phone must be numeric.");
        this.phone = phone;
    }

//  /
//            int sum = 0;
//            for (int i = 0; i < 9; i++) {
//                sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
//            }
//            int firstCheckDigit = 11 - (sum % 11);
//            firstCheckDigit = (firstCheckDigit >= 10) ? 0 : firstCheckDigit;
//
//            sum = 0;
//            for (int i = 0; i < 10; i++) {
//                sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
//            }
//            int secondCheckDigit = 11 - (sum % 11);
//            secondCheckDigit = (secondCheckDigit >= 10) ? 0 : secondCheckDigit;
//
//            return firstCheckDigit == Character.getNumericValue(cpf.charAt(9)) &&
//                    secondCheckDigit == Character.getNumericValue(cpf.charAt(10));
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
}


package br.com.leonardohmleal.avaliacao_final.service;

import br.com.leonardohmleal.avaliacao_final.dto.CustomerDto;
import br.com.leonardohmleal.avaliacao_final.exception.ResourceNotFoundException;
import br.com.leonardohmleal.avaliacao_final.mapper.CustomModelMapper;
import br.com.leonardohmleal.avaliacao_final.model.BookModel;
import br.com.leonardohmleal.avaliacao_final.model.CustomerModel;
import br.com.leonardohmleal.avaliacao_final.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public CustomerDto create(CustomerDto customerDto){
        CustomerModel customerModel = CustomModelMapper.parseObject(customerDto, CustomerModel.class);
        return CustomModelMapper.parseObject(repository.save(customerModel), CustomerDto.class);
    }

    public CustomerDto findById(long id){
        CustomerModel customerModel = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Cliente não encontrado!"));
        return CustomModelMapper.parseObject(customerModel, CustomerDto.class);
    }

    public CustomerDto update(CustomerDto customerDto){
        CustomerModel customerModel = repository.findById(customerDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Cliente não encontrado!")
        );
        customerModel.setGender(customerDto.getGender());
        customerModel.setFullName(customerDto.getFullName());
        customerModel.setBook(CustomModelMapper.parseObject(customerDto.getBook(), BookModel.class));
        return CustomModelMapper.parseObject(repository.save(customerModel), CustomerDto.class);
    }

    public void delete(long id){
        CustomerModel customerModel = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Cliente não encontrado!")
        );
        repository.delete(customerModel);
    }

    public Page<CustomerDto> findAll(Pageable pageable){
        var customers = repository.findAll(pageable);
        return customers.map( c -> CustomModelMapper.parseObject(c, CustomerDto.class));
    }}
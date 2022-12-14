package com.app.MyMovies.service;

import com.app.MyMovies.dto.ReportClientDto;
import com.app.MyMovies.dto.ResponseDto;
import com.app.MyMovies.entities.Client;

import com.app.MyMovies.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    private final String CLIENT_REGISTERED="el correo ya esta registrado";
    private final String CLIENT_SUCCESS="el cliente  se registró correctamente";

    @Autowired
    ClientRepository repository;

    public Iterable<Client> get() {
        Iterable<Client> response = repository.getAll();
        return response;
    }

    public ReportClientDto getReport() {
        Optional<Client> client = repository.findById("6380442df71ad74770fc57e1");
        ReportClientDto reportClientDto= new ReportClientDto();
        reportClientDto.birthDate=client.get().getBirthDate();
        reportClientDto.email=client.get().getEmail();
        reportClientDto.id=client.get().getId();
        return reportClientDto;
    }

    public ResponseDto create(Client request) {

        ResponseDto response = new ResponseDto();
        List<Client> clients = repository.getByEmail(request.getEmail());
        if(clients.size()>0){
            response.status=false;
            response.message=CLIENT_REGISTERED;
        }else{
            repository.save(request);
            response.status=true;
            response.message=CLIENT_SUCCESS;
            response.id= request.getId();
        }
        return response;
    }

    public Client update(Client client) {
        Client clientToUpdate = new Client();

        Optional<Client> currentClient = repository.findById(client.getId());
        if (!currentClient.isEmpty()) {            
            clientToUpdate = client;
            clientToUpdate=repository.save(clientToUpdate);
        }
        return clientToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}

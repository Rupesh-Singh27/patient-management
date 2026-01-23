package com.pm.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingServiceGrpcClient {

    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;


    //localhost:9001/BillingService/CreatePatientAccount -- local dev
    //aws.grpc:123123/BillingService/CreatePatientAccount -- prod

    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String billingServiceGrpcServerAddress,
            @Value("${billing.service.grpc.port:9001}") int billingServiceGrpcServerPort
    ) {
        log.info("Connecting to Billing Service GRPC at {} : {}", billingServiceGrpcServerAddress, billingServiceGrpcServerPort);

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(
                billingServiceGrpcServerAddress,
                billingServiceGrpcServerPort).usePlaintext().build();

        blockingStub = BillingServiceGrpc.newBlockingStub(managedChannel);
    }

    public BillingResponse createBillingAccount(String patientId, String name, String email){
        BillingRequest billingRequest = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();

        BillingResponse billingResponse = blockingStub.createBillingAccount(billingRequest);

        log.info("Received response from billing service via GRPC: {}", billingResponse);
        return billingResponse;
    }
}

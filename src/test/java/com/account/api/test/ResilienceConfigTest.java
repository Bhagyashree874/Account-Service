package com.account.api.test;

import com.bhagya.account.api.config.ResilienceConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.RetryConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ResilienceConfigTest {

    @InjectMocks
    private ResilienceConfig resilienceConfig;

    @Test
    void retryConfig() {
        // Act
        RetryConfig retryConfig = resilienceConfig.retryConfig();

        // Assert
        assertNotNull(retryConfig);
        assertEquals(3, retryConfig.getMaxAttempts());

    }

    @Test
    void circuitBreakerConfig() {
        // Act
        CircuitBreakerConfig circuitBreakerConfig = resilienceConfig.circuitBreakerConfig();

        // Assert
        assertNotNull(circuitBreakerConfig);
        assertEquals(50, circuitBreakerConfig.getFailureRateThreshold());
        assertEquals(Duration.ofSeconds(10), circuitBreakerConfig.getWaitDurationInOpenState());
    }
}

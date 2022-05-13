package com.example.oldiebutgoldie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OldieControllerTest {

    @Mock
    OldieController oldieControllerMock;

    @BeforeEach
    void setUp() {
        oldieControllerMock = Mockito.mock(OldieController.class);
    }

    @Test
    public void testConnectToDatabase() throws SQLException {
        String dsn = "jdbc:mysql://localhost/"
                + "oldiebutgoldie"
                + "?user=Sam"
                + "&password=P@ssw0rd";

        Connection connection = DriverManager.getConnection(dsn);

        when(oldieControllerMock.connectToDatabase()).thenReturn(connection);
        assertEquals(oldieControllerMock.connectToDatabase(), connection);
    }
}

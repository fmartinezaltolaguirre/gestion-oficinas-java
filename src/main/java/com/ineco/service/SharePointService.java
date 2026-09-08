package com.ineco.service;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.InputStream;

@Service
public class SharePointService {

    // Estas propiedades se leerán de forma segura desde tu application.yml
    @Value("${azure.activedirectory.tenant-id}")
    private String tenantId;

    @Value("${azure.activedirectory.client-id}")
    private String clientId;

    @Value("${azure.activedirectory.client-secret}")
    private String clientSecret;

    @Value("${sharepoint.site-id}")
    private String siteId;

    private GraphServiceClient graphClient;

    /**
     * Inicializa el cliente oficial de Microsoft Graph una vez cargadas las propiedades.
     */
    @PostConstruct
    public void inicializarConexion() {
        // Configuramos la autenticación de la aplicación en Azure Entra ID [1]
        ClientSecretCredential credential = new ClientSecretCredentialBuilder()
                .tenantId(tenantId)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();

        // Creamos la instancia del cliente para operar en la nube de Microsoft 365 [1]
        this.graphClient = new GraphServiceClient(credential);
    }

    /**
     * Sube un fichero técnico (PDF, planos, informes) directamente al SharePoint corporativo.
     * Reemplaza la antigua gestión de adjuntos locales que limitaba a Microsoft Access.
     */
    public String subirDocumento(String nombreArchivo, InputStream contenidoArchivo, long tamano) {
        try {
            // Ruta destino dentro de la biblioteca de documentos de tu sitio de SharePoint
            String rutaDestino = "/drive/root:/" + nombreArchivo + ":/content";

            // Realizamos la subida del flujo de datos a través de Microsoft Graph [1]
            this.graphClient.sites()
                    .bySiteId(siteId)
                    .drive()
                    .root()
                    .itemWithPath(rutaDestino)
                    .content()
                    .put(contenidoArchivo);

            return "Archivo '" + nombreArchivo + "' subido correctamente a SharePoint.";
        } catch (Exception e) {
            throw new RuntimeException("Error crítico al subir el fichero a SharePoint: " + e.getMessage(), e);
        }
    }

    /**
     * Recupera un documento de SharePoint en formato de flujo de datos para que el usuario pueda descargarlo.
     */
    public InputStream descargarDocumento(String itemId) {
        try {
            return this.graphClient.sites()
                    .bySiteId(siteId)
                    .drive()
                    .items()
                    .byDriveItemId(itemId)
                    .content()
                    .get();
        } catch (Exception e) {
            throw new RuntimeException("Error crítico al descargar el fichero desde SharePoint: " + e.getMessage(), e);
        }
    }
}

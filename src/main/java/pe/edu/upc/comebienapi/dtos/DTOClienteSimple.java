    package pe.edu.upc.comebienapi.dtos;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class DTOClienteSimple {
        private Long id;
        private String nombre;
        private String username;
    }

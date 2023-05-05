package com.example.demo.gestionMagasin.exempleEntity;




public class ServiceDto {
	
	private Long idService;
	
	public ServiceDto() {
		
	}
	public ServiceDto(Long idService) {
		this.idService = idService;
		
	}
	 public Long getIdService() {
			return idService;
		}
		public void setIdService(Long idService) {
			this.idService=idService;
		}

}

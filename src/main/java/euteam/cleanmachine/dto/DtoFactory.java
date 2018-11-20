package euteam.cleanmachine.dto;

import euteam.cleanmachine.model.facility.Program;

public class DtoFactory {

    public static ProgramDto programToDto(Program program){
        ProgramDto programDto = new ProgramDto();
        programDto.setName(program.getName());
        programDto.setDuration(program.getDuration());
        programDto.setCost(program.getCost());
        programDto.setId(program.getId());
        return  programDto;
    }


}

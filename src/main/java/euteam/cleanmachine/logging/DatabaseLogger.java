package euteam.cleanmachine.logging;

import euteam.cleanmachine.commands.MachineCommand;
import euteam.cleanmachine.dao.CommandLogDao;
import euteam.cleanmachine.model.facility.CommandLog;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DatabaseLogger implements MachineCommandLogger {

    @Autowired
    private CommandLogDao commandLogDao;

    @Override
    public void logMachineCommand(MachineCommand machineCommand) {
        CommandLog log = new CommandLog();

        log.setCommandName(machineCommand.getCommandName());
        log.setDate(new Date());
        log.setUser(machineCommand.getUser());
        log.setMachine(machineCommand.getMachine());

        if(machineCommand.getProgram() != null) {
            log.setProgram(machineCommand.getProgram());
        }

        commandLogDao.save(log);
    }
}

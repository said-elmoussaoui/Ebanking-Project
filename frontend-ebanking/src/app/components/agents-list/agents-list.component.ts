import { Component, OnInit } from '@angular/core';
import { Agent } from 'app/interfaces/agent';
import { AgentServiceService } from 'app/services/agent-service.service';

@Component({
  selector: 'app-agents-list',
  templateUrl: './agents-list.component.html',
  styleUrls: ['./agents-list.component.css']
})
export class AgentsListComponent implements OnInit {
  public agents: Agent[];

  constructor(private agentService: AgentServiceService) { 

  }
  ngOnInit(): void {
    this.getAgents();
  }

  // ngOnInit(): void {
  //   this.getAgents();
  // }
 public getAgents(): void {
     this.agentService.getAll().subscribe((response: Agent[])=>{this.agents=response; console.log(this.agents)});
     console.log(this.agents);
    
  }
  

}

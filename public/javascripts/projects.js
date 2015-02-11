var projects = {};


projects.controller = function(){
    projects.vm.init();
}

projects.vm = {}
projects.vm.init = function(){
    this.projectList = m.request({method: "GET", url: "projects"});
}

projects.view = function(){
    return [m("ul",
        projects.vm.projectList().map(function(project){
            return m("li",project.name);
        })),
        m("form",{action:"add"},
            ["Project name",
                m("br"),
                m("input",{type: "text", name: "Project name"}),
            m("br"),
            m("input", {type: "submit"}, {value: "Submit"})])];
}

//m.module(document.getElementById("app"),projects);
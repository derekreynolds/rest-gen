var Generator = require('yeoman-generator');

String.prototype.toUpperCaseFirstChar = function() {
    return this.substr( 0, 1 ).toUpperCase() + this.substr( 1 );
}

String.prototype.toLowerCaseFirstChar = function() {
    return this.substr( 0, 1 ).toLowerCase() + this.substr( 1 );
}

module.exports = class extends Generator {

	prompting() {
	    return this.prompt([{
		  type    : 'input',
		  name    : 'username',
		  message : 'What\'s your username?',
		  store   : true
		},
	    {
	      type    : 'input',
	      name    : 'package',
	      message : 'Enter root package to create the files for e.g. com.mycompany.component',
	      default : 'ie.evolvingreality.component',
	      store   : true
	    }, {
	      type    : 'input',
	      name    : 'entity',
	      message : 'Enter the name of the entity to create the REST service for?'
	    }]).then((answers) => {
	      this.username = answers.username;
	      this.entity = answers.entity.toUpperCaseFirstChar();
	      this.entityCamelCase = answers.entity.toLowerCaseFirstChar();
	      this.packageName = answers.package;

	      this.log(`Generating a REST service for ${answers.entity} in ${answers.package}`);
	    });
  	}

  	writing() {
  		var packagePath = 'src/main/java/' + this.packageName.replace(/\./g,'/');
  		var testPackagePath = 'src/test/java/' + this.packageName.replace(/\./g,'/')
	    this.fs.copyTpl(
	      	this.templatePath('entity.java'),
	      	this.destinationPath(`${packagePath}/domain/model/${this.entity}.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}` }
	    );
	    this.fs.copyTpl(
	      	this.templatePath('entityService.java'),
	      	this.destinationPath(`${packagePath}/service/${this.entity}Service.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
	    this.fs.copyTpl(
	      	this.templatePath('entityServiceImpl.java'),
	      	this.destinationPath(`${packagePath}/service/${this.entity}ServiceImpl.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
	    this.fs.copyTpl(
	      	this.templatePath('entityRepository.java'),
	      	this.destinationPath(`${packagePath}/repository/${this.entity}Repository.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
	    this.fs.copyTpl(
	      	this.templatePath('entityController.java'),
	      	this.destinationPath(`${packagePath}/web/rest/${this.entity}Controller.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
	    this.fs.copyTpl(
	      	this.templatePath('entityValidator.java'),
	      	this.destinationPath(`${packagePath}/web/rest/validator/${this.entity}Validator.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
	    this.fs.copyTpl(
	      	this.templatePath('entityResource.java'),
	      	this.destinationPath(`${packagePath}/web/rest/resource/${this.entity}Resource.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
	    this.fs.copyTpl(
	      	this.templatePath('entityResourceAssembler.java'),
	      	this.destinationPath(`${packagePath}/web/rest/resource/${this.entity}ResourceAssembler.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
	   	this.fs.copyTpl(
	      	this.templatePath('entityNotFoundException.java'),
	      	this.destinationPath(`${packagePath}/domain/exception/${this.entity}NotFoundException.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
	    this.fs.copyTpl(
	      	this.templatePath('test/entityServiceImplTest.java'),
	      	this.destinationPath(`${testPackagePath}/service/${this.entity}ServiceImplTest.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
	    this.fs.copyTpl(
	      	this.templatePath('test/entityServiceImplIT.java'),
	      	this.destinationPath(`${testPackagePath}/service/${this.entity}ServiceImplIT.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
	    this.fs.copyTpl(
	      	this.templatePath('test/entityTestUtil.java'),
	      	this.destinationPath(`${testPackagePath}/domain/${this.entity}TestUtil.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
	   	this.fs.copyTpl(
	      	this.templatePath('test/entityControllerTest.java'),
	      	this.destinationPath(`${testPackagePath}/web/rest/${this.entity}ControllerTest.java`),
	      	{ entity: `${this.entity}`,
	      	  package: `${this.packageName}`,
	      	  username: `${this.username}`,
	      	  entityCamelCase: `${this.entityCamelCase}` }
	    );
  	}
};


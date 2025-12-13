#!/usr/bin/env node

const { Command } = require("commander");
const fs = require("fs");
const path = require("path");

const program = new Command();

program
  .command("module <name>")
  .description("Generate Spring Boot module structure")
  .action((name) => {
    const basePath = path.join(
      process.cwd(),
      "src/main/java/com/vinci/devmatch/modules",
      name
    );

    const folders = [
      "controller",
      "dto",
      "entity",
      "enums",
      "mapper",
      "repository",
      "service",
      "usecase",
      "validation",
    ];

    fs.mkdirSync(basePath, { recursive: true });

    folders.forEach((folder) => {
      fs.mkdirSync(path.join(basePath, folder), { recursive: true });
    });

    console.log(`✔ Module '${name}' generated`);
  });

program.parse(process.argv);

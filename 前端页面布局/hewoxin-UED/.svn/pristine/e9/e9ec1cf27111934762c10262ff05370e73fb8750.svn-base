/**
 * Created by chenqb on 2015/7/4.
 */
module.exports = function(grunt) {
    grunt.initConfig({
        less: {
            compileCore: {
                options: {
                    strictMath: true,
                    outputSourceFiles: true
                },
                src: 'src/less/*.less',
                dest: 'assets/css/hewoxin.css'
            }
        },
        cssmin: {
            options: {
                compatibility: 'ie8',
                keepSpecialComments: '*',
                advanced: false
            },
            minifyCore: {
                src: 'assets/css/hewoxin.css',
                dest: 'assets/css/hewoxin.min.css'
            }
        },
        jshint: {
            options: {
                globals: {
                    jQuery: true
                }
            },
            files: ['src/js/*.js']
        },
        uglify: {
            options: {
                banner: '/** ! hewoxin <%= grunt.template.today("yyyy-mm-dd") %> */\n'
            },
            dist: {
                files: [{
                    expand: true,
                    cwd: 'src/js',
                    src: '**/*.js',
                    dest: 'assets/js',
                    ext: ".min.js"
                }]
            }
        },
        watch: {
            change: {
                files: ['<%= less.compileCore.src %>','<%= jshint.files %>'],
                tasks: ['less','cssmin','jshint','uglify']
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-watch');

    // 只需在命令行上输入"grunt"，就会执行default task
    grunt.registerTask('default', ['watch']);
};
package com.mana.innovative.utilities.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;

/**
 * The class FileRenaming is for todo.
 * Created by BLOOM on 10/20/2015
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
public class FileRenaming {

    private Logger logger = LoggerFactory.getLogger( FileRenaming.class );

    public static void main( String args[] ) {

        int choice = Integer.parseInt( JOptionPane.showInputDialog( null, "Enter 1 for file extension change, 2 for New Name" ) );

        JFileChooser chooser = new JFileChooser( );
        chooser.setCurrentDirectory( new java.io.File( "." ) );
        chooser.setDialogTitle( "Choose the folder whose files are to be renamed" );
        chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
        chooser.setAcceptAllFileFilterUsed( false );
        File currentDirectory;
        if ( chooser.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {
//            System.out.println( "getCurrentDirectory(): " + chooser.getCurrentDirectory( ) );
//            System.out.println( "getSelectedFile() : " + chooser.getSelectedFile( ) );
            currentDirectory = chooser.getSelectedFile( );
        } else {
            currentDirectory = null;
//            System.out.println( "No Selection " );
        }
        if ( currentDirectory == null ) {
            JOptionPane.showMessageDialog( null, "Directory is null", "Alert!!!", JOptionPane.ERROR_MESSAGE );
            return;
        }
        String oldExtension = "", newExtension = "";
        if ( choice == 1 ) {
            oldExtension = JOptionPane.showInputDialog( null, "Rename for ", "Enter Extension of files to be " +
                    "renamed" );
            newExtension = JOptionPane.showInputDialog( null, "Rename to", "Enter Extension of files to be " +
                    "renamed to" );
        }
        if ( choice == 2 ) {
            newExtension = JOptionPane.showInputDialog( null, "Rename to", "Enter New File name to be used with list " +
                    "numbering of files to be renamed to" );
        }
        if ( oldExtension == null || newExtension == null ) {
            JOptionPane.showMessageDialog( null, "No rename parameters provided", "Alert!!!", JOptionPane.ERROR_MESSAGE );
            return;
        }
//        System.out.println( oldExtension + "   new ext: " + newExtension );
        FileRenaming fileRenaming = new FileRenaming( );
        if ( choice == 1 ) {
            fileRenaming.renameFileExt( currentDirectory, oldExtension, newExtension );
        }
        if ( choice == 2 ) {
            fileRenaming.renameFileNameWithNewName( currentDirectory, newExtension );
        }
    }

    public boolean checkFile( final File currentDirectory ) {

        return currentDirectory != null && currentDirectory.isDirectory( ) && currentDirectory.canWrite( ) &&
                currentDirectory.canRead( );
    }

    public void renameFileExt( File currentDirectory, String oldExt, String newExt ) {

        if ( !checkFile( currentDirectory ) ) {
            return;
        }
        File files[] = currentDirectory.listFiles( );
        if ( files != null && files.length > 0 ) {
            for ( File file : files ) {
                if ( file == null || file.isDirectory( ) ) {
                    continue;
                }
                String abstractFileName = file.getPath( );
                if ( abstractFileName.endsWith( oldExt ) ) {
                    abstractFileName = abstractFileName.substring( 0, abstractFileName.lastIndexOf( oldExt ) ) + newExt;
                    System.out.println( "newName is" + currentDirectory.getAbsolutePath( ) + "/" + abstractFileName );
                    boolean isRenamed = file.renameTo( new File( abstractFileName ) );
                    if ( !isRenamed ) {
                        System.out.println( "Error occurred while renaming: " + abstractFileName );
                    }
                }
            }
        }
    }

    public void renameFileNameWithNewName( File currentDirectory, String newName ) {

        if ( !checkFile( currentDirectory ) ) {
            return;
        }
        int i = 1;
        File files[] = currentDirectory.listFiles( );
        if ( files != null && files.length > 0 ) {
            for ( File file : files ) {
                if ( file == null || file.isDirectory( ) ) {
                    continue;
                }
                String abstractFileName = file.getPath( );
                abstractFileName = newName + i + abstractFileName.substring( abstractFileName.lastIndexOf( "." ),
                        abstractFileName.length( ) );
                i++;
                System.out.println( "newName is" + currentDirectory.getAbsolutePath( ) + "/" + abstractFileName );
                boolean isRenamed = file.renameTo( new File( currentDirectory.getAbsolutePath( ) + "/" + abstractFileName
                ) );
                if ( !isRenamed ) {
                    System.out.println( "Error occurred while renaming: " + abstractFileName );
                }
            }
        }
    }
}
